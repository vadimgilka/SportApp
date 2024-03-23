import { Module } from '@nestjs/common';
import { TrainsService } from './trains.service';
import { ExercisesModule } from 'src/exercises/exercises.module';
import { PrismaModule } from 'src/prisma/prisma.module';
import { TrainsController } from './trains.controller';
import { PrismaService } from 'src/prisma/prisma.service';

@Module({
  imports: [PrismaModule, ExercisesModule],
  providers: [TrainsService, PrismaService],
  controllers: [TrainsController],
  exports: [TrainsService],
})
export class TrainsModule {}
