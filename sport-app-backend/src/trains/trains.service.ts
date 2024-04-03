import { PrismaService } from './../prisma/prisma.service';
import {
  BadRequestException,
  ForbiddenException,
  Injectable,
  NotFoundException,
  UseFilters,
} from '@nestjs/common';
import { Exercise, ExerciseOnTrain, Prisma, Train } from '@prisma/client';
import { CreateTrainDto, UpdateTrainDto } from './trains.dto';
import { MAX_EXERCISE_IN_TRAIN, trainPage } from './constant';
import { uniq } from 'lodash';
import { ExercisesService } from 'src/exercises/exercises.service';
import { ExerciseTrainDto } from 'src/exercises/exercises.dto';
import e from 'express';
import { HttpExceptionFilter } from 'src/utils/filters/httpexcepion.filter';

@Injectable()
@UseFilters(HttpExceptionFilter)
export class TrainsService {
  constructor(
    private exerciseService: ExercisesService,
    private prisma: PrismaService,
  ) {}

  async create(data: CreateTrainDto): Promise<Train> {
    try {
      await this.checkExercises(data.exercises);
      const connection = this.formatDataForCreation(data.exercises);

      const createData = {
        name: data.name,
        description: data.description,
        author: {
          connect: {
            id: data.author_id,
          },
        },
        exercises: {
          create: connection,
        },
      };

      const train = await this.prisma.train.create({
        data: createData,
        include: {
          exercises: {
            include: {
              Exercise: true,
            },
          },
        },
      });
      return train;
    } catch (exception) {
      throw exception;
    }
  }

  async update(params: {
    where: Prisma.TrainWhereUniqueInput;
    data: UpdateTrainDto;
  }): Promise<Train> {
    const { where, data } = params;
    try {
      await this.checkExercises(data.exercises);
    } catch (exception) {
      throw exception;
    }

    const updates = this.formatDataForUpdating(data.id, data.exercises);

    const updateData = {
      name: data.name,
      description: data.description,
      author: {
        connect: {
          id: data.author_id,
        },
      },
      exercises: {
        upsert: updates,
      },
    };

    const train = await this.train(where);

    if (!train) {
      throw new NotFoundException('train not found');
    }

    if (!this.isHasRights(train, data.author_id)) {
      throw new ForbiddenException('not have rights for deleted this train');
    }

    await this.deleteConnections(train.id, data.exercises);

    return this.prisma.train.update({
      data: updateData,
      where,
      include: {
        exercises: {
          include: {
            Exercise: true,
          },
        },
      },
    });
  }

  async train(where: Prisma.TrainWhereUniqueInput): Promise<Train | null> {
    const train = await this.prisma.train.findUnique({
      where,
      include: {
        exercises: {
          include: {
            Exercise: true,
          },
        },
      },
    });

    if (!train) {
      throw new NotFoundException('train not found');
    }
    return train;
  }

  async findById(id: number) {
    return this.train({ id: id });
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
      include: {
        exercises: {
          include: {
            Exercise: true,
          },
        },
      },
    });

    return trains;
  }

  async trainsByPage(params: {
    pageNumber: number;
    cursor?: Prisma.TrainWhereUniqueInput;
    where?: Prisma.TrainWhereInput;
    orderBy?: Prisma.TrainOrderByWithRelationInput;
  }): Promise<Train[]> {
    const { pageNumber, cursor, where, orderBy } = params;

    const skip = trainPage.size * (pageNumber - 1);
    const take = trainPage.size;
    return this.trains({ skip, take, cursor, where, orderBy });
  }

  private async checkExercises(exercises: ExerciseTrainDto[]) {
    if (exercises) {
      if (exercises.length > MAX_EXERCISE_IN_TRAIN) {
        throw new BadRequestException(
          MAX_EXERCISE_IN_TRAIN + ' is max count of exercises in one train',
        );
      }

      let incorrectExercisesCnt = 0;
      const uniqExercisesId = uniq(
        exercises.map((x) => {
          if (!x.id) {
            incorrectExercisesCnt++;
          } else {
            return x.id;
          }
        }),
      );

      if (incorrectExercisesCnt) {
        throw new BadRequestException(
          'Incorrect format for exercises in train',
        );
      }

      if (!uniqExercisesId) {
        throw new BadRequestException(
          'Incorrect format for exercises in train',
        );
      }

      const data = await this.exerciseService.exercises({
        where: { id: { in: uniqExercisesId } },
      });

      if (data.length != uniqExercisesId.length) {
        throw new NotFoundException('some exercises in train are not exists');
      }
    }

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

      if (!exercise.exerciseNumber || exercise.exerciseNumber <= 0) {
        throw new BadRequestException(
          'Incorrect exercise format: exerciseNumber is missing or has incorrect format',
        );
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

  private formatDataForCreation(exercises: ExerciseTrainDto[]) {
    const result = [];
    for (const exercise of exercises) {
      const tmp = { ...exercise };
      delete tmp.id;
      const data = {
        ...tmp,
        exerciseNumber: exercise.exerciseNumber,
        Exercise: {
          connect: {
            id: exercise.id,
          },
        },
      };

      result.push(data);
    }

    return result;
  }

  private formatDataForUpdating(
    trainId: number,
    exercises: ExerciseTrainDto[],
  ) {
    const result = [];
    for (const exercise of exercises) {
      const tmp = { ...exercise };
      delete tmp.id;
      const data = {
        where: {
          unique_trainId_exerciseNumber: {
            trainId: trainId,
            exerciseNumber: exercise.exerciseNumber,
          },
        },

        update: {
          ...tmp,
          Exercise: {
            connect: {
              id: exercise.id,
            },
          },
        },

        create: {
          ...tmp,
          Exercise: {
            connect: {
              id: exercise.id,
            },
          },
        },
      };

      result.push(data);
    }

    return result;
  }

  private async deleteConnections(
    trainId: number,
    exercises: ExerciseTrainDto[],
  ) {
    const exerciseNumbers = exercises.map(
      (exercise) => exercise.exerciseNumber,
    );
    return this.prisma.exerciseOnTrain.deleteMany({
      where: {
        trainId: trainId,
        exerciseNumber: { not: { in: exerciseNumbers } },
      },
    });
  }

  private isHasRights(train: Train, userId: number) {
    return train.author_id == userId;
  }
}
