import { Injectable } from '@nestjs/common';
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
    return this.prisma.exercise.update({
      data,
      where,
    });
  }

  async deleteExercise(
    where: Prisma.ExerciseWhereUniqueInput,
  ): Promise<Exercise> {
    return this.prisma.exercise.delete({ where });
  }

  async exercises(params: {
    skip?: number;
    take?: number;
    cursor?: Prisma.ExerciseWhereUniqueInput;
    where?: Prisma.ExerciseWhereInput;
    orderBy?: Prisma.ExerciseOrderByWithRelationInput;
  }): Promise<Exercise[]> {
    const {skip, take, cursor, where, orderBy} = params;
    return this.prisma.exercise.findMany({skip, take, cursor, where, orderBy});
  }

  async exercise(): Promise<Exercise> {}
}
