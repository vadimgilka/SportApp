import { PrismaService } from './../prisma/prisma.service';
import {
  BadRequestException,
  Injectable,
  NotFoundException,
} from '@nestjs/common';
import { Exercise, Prisma, Train } from '@prisma/client';
import { CreateTrainDto, UpdateTrainDto } from './trains.dto';
import { MAX_EXERCISE_IN_TRAIN } from './constant';
import { uniq } from 'lodash';
import { ExercisesService } from 'src/exercises/exercises.service';
import { ExerciseTrainDto } from 'src/exercises/exercises.dto';

@Injectable()
export class TrainsService {
  constructor(
    private prisma: PrismaService,
    private exerciseService: ExercisesService,
  ) {}

  async create(dto: CreateTrainDto): Promise<Train> {
    if (dto.exercises.length > MAX_EXERCISE_IN_TRAIN) {
      throw new BadRequestException(
        MAX_EXERCISE_IN_TRAIN + ' is max count of exercises in one train',
      );
    }

    this.checkExercises(dto.exercises);

    const data = {
      name: dto.name,
      description: dto.description,
      author: {
        connect: {
          id: dto.author_id,
        },
      },
    };

    const train = await this.prisma.train.create({ data });

    return train;
  }

  async update(params: {
    where: Prisma.ExerciseWhereUniqueInput;
    data: UpdateTrainDto;
  }): Promise<Train> {
    return null;
  }

  async train(where: Prisma.TrainWhereUniqueInput): Promise<Train | null> {
    const train = this.prisma.train.findUnique({ where });

    if (!train) {
      throw new NotFoundException('train not found');
    }

    return train;
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
      const uniqExercisesId = uniq(exercises.map((x) => x.id));
    }
  }
}