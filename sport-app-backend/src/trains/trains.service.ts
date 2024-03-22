import { PrismaService } from './../prisma/prisma.service';
import {
  BadRequestException,
  ForbiddenException,
  Injectable,
  NotFoundException,
} from '@nestjs/common';
import { Exercise, ExerciseOnTrain, Prisma, Train } from '@prisma/client';
import { CreateTrainDto, UpdateTrainDto } from './trains.dto';
import { MAX_EXERCISE_IN_TRAIN } from './constant';
import { uniq } from 'lodash';
import { ExercisesService } from 'src/exercises/exercises.service';
import { ExerciseTrainDto } from 'src/exercises/exercises.dto';

@Injectable()
export class TrainsService {
  constructor(
    private exerciseService: ExercisesService,
    private prisma: PrismaService,
  ) {}

  async create(data: CreateTrainDto): Promise<Train> {
    this.checkExercises(data.exercises);

    const createData = {
      name: data.name,
      description: data.description,
      author: {
        connect: {
          id: data.author_id,
        },
      },
    };

    const train = await this.prisma.train.create({ data: createData });
    const exerciseTrain = this.formatDataForConnection(
      train.id,
      data.exercises,
    );
    await this.prisma.exerciseOnTrain.createMany({ data: exerciseTrain });

    return train;
  }

  async update(params: {
    where: Prisma.TrainWhereUniqueInput;
    data: UpdateTrainDto;
  }): Promise<Train> {
    const { where, data } = params;
    this.checkExercises(data.exercises);

    const updateData = {
      name: data.name,
      description: data.description,
      author: {
        connect: {
          id: data.author_id,
        },
      },
    };

    const train = await this.train(where);

    if (!train) {
      throw new NotFoundException('train not found');
    }

    if (!this.isHasRights(train, data.author_id)) {
      throw new ForbiddenException('not have rights for deleted this train');
    }

    return  this.prisma.train.update({data : updateData, where});
    
  }

  async train(where: Prisma.TrainWhereUniqueInput): Promise<Train | null> {
    const train = await this.prisma.train.findUnique({ where });

    if (!train) {
      throw new NotFoundException('train not found');
    }
    return train;
  }

  async findById(id : number){
    return this.train({id : id});
  }

  async trains(params: {
    skip?: number;
    take?: number;
    cursor?: Prisma.TrainWhereUniqueInput;
    where?: Prisma.TrainWhereInput;
    orderBy?: Prisma.TrainOrderByWithRelationInput;
  }): Promise<Train[]> {
    const { skip, take, cursor, where, orderBy } = params;
    const trains = this.prisma.train.findMany({
      skip,
      take,
      cursor,
      where,
      orderBy,
    });

    return trains;
  }

  private async checkExercises(exercises: ExerciseTrainDto[]) {
    if (exercises) {
      if (exercises.length > MAX_EXERCISE_IN_TRAIN) {
        throw new BadRequestException(
          MAX_EXERCISE_IN_TRAIN + ' is max count of exercises in one train',
        );
      }

      const uniqExercisesId = uniq(exercises.map((x) => x.id));
      const data = await this.exerciseService.exercises({
        where: { id: { in: uniqExercisesId } },
      });

      if (data.length != exercises.length) {
        throw new NotFoundException('some exercises in train are not exists');
      }
    }
  }

  async delete(
    where: Prisma.TrainWhereUniqueInput,
    userId: number,
  ): Promise<Train> {
    const train = await this.train(where);

    if (!train) {
      throw new NotFoundException('train not found');
    }

    if (!this.isHasRights(train, userId)) {
      throw new ForbiddenException('not have rights for deleted this train');
    }

    return this.prisma.train.delete({ where });
  }

  private formatDataForConnection(
    train_id: number,
    exercises: ExerciseTrainDto[],
  ): Prisma.ExerciseOnTrainCreateManyInput[] {
    const result = [];
    let cnt = 0;
    for (const exercise of exercises) {
      if (!exercise.time && !exercise.repetition) {
        throw new BadRequestException(
          'Incorrect exercise format: exercise should has time or repetition',
        );
      }

      if (exercise.time && exercise.repetition) {
        throw new BadRequestException(
          'Incorrect exercise format: exercise should has time or repetition',
        );
      }

      const data: Prisma.ExerciseOnTrainCreateInput = {
        ...exercise,
        Exercise: { connect: { id: exercise.id } },
        Train: { connect: { id: train_id } },
        exerciseNumber: cnt++,
      };

      result.push(data);
    }

    return result;
  }

  private isHasRights(train: Train, userId: number) {
    return train.author_id == userId;
  }
}
