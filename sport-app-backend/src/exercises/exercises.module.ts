import { Module } from '@nestjs/common';
import { ExercisesController } from './exercises.controller';
import { ExercisesService } from './exercises.service';
import { PrismaService } from 'nestjs-prisma';

@Module({
  providers: [ExercisesService, PrismaService],
  controllers: [ExercisesController],
  exports: [ExercisesService]
})
export class ExercisesModule {}
