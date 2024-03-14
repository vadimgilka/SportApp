import {
  ForbiddenException,
  Injectable,
  NotFoundException,
} from '@nestjs/common';
import { Exercise, Prisma } from '@prisma/client';
import { PrismaService } from 'nestjs-prisma';
import { UpdateExerciseDto } from './exercises.dto';

@Injectable()
export class ExercisesService {
  constructor(private prisma: PrismaService) {}

  async createExercise(data: Prisma.ExerciseCreateInput): Promise<Exercise> {
    return this.prisma.exercise.create({ data });
  }

  async updateExercise(params: {
    where: Prisma.ExerciseWhereUniqueInput;
    data: UpdateExerciseDto;
  }): Promise<Exercise> {
    const { where, data } = params;
    const exercise = await this.exercise(where);

    if (!exercise) {
      throw new NotFoundException('exercise not found');
    }

    if (!this.isHasRights(exercise, data.author_id)) {
      throw new ForbiddenException(
        'not have rights for updating this exercise',
      );
    }

    return this.prisma.exercise.update({
      data,
      where,
    });
  }

  async deleteExercise(
    where: Prisma.ExerciseWhereUniqueInput,
    userId: number,
  ): Promise<Exercise> {
    const exercise = await this.exercise(where);

    if (!exercise) {
      throw new NotFoundException('exercise not found');
    }

    if (!this.isHasRights(exercise, userId)) {
      throw new ForbiddenException('not have rights for deleted this exercise');
    }

    return this.prisma.exercise.delete({ where });
  }

  async exercises(params: {
    skip?: number;
    take?: number;
    cursor?: Prisma.ExerciseWhereUniqueInput;
    where?: Prisma.ExerciseWhereInput;
    orderBy?: Prisma.ExerciseOrderByWithRelationInput;
  }): Promise<Exercise[]> {
    const { skip, take, cursor, where, orderBy } = params;
    const exercises = this.prisma.exercise.findMany({
      skip,
      take,
      cursor,
      where,
      orderBy,
    });

    return exercises;
  }

  async exercise(
    where: Prisma.ExerciseWhereUniqueInput,
  ): Promise<Exercise | null> {
    const exercise = await this.prisma.exercise.findUnique({ where });
    console.log(exercise)
    if (!exercise) {
      throw new NotFoundException('exercise not found');
    }

    return exercise;
  }

  async findById(id: number) {
    return this.exercise({ id: id });
  }

  private isHasRights(exercise: Exercise, userId: number) {
    return exercise.author_id == userId;
  }
}
