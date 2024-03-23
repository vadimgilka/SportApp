import { Module } from '@nestjs/common';
import { ExercisesController } from './exercises.controller';
import { ExercisesService } from './exercises.service';
import { PrismaService } from 'nestjs-prisma';
import { FileUtils } from 'src/files/files.utils';
import { MulterModule } from '@nestjs/platform-express';
import { PrismaModule } from 'src/prisma/prisma.module';

@Module({
  imports : [PrismaModule],
  providers: [ExercisesService],
  controllers: [ExercisesController],
  exports: [ExercisesService]
})
export class ExercisesModule {}
