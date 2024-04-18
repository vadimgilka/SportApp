import { Test, TestingModule } from '@nestjs/testing';
import { TrainsService } from './trains.service';
import { PrismaService } from 'src/prisma/prisma.service';
import { ExercisesService } from 'src/exercises/exercises.service';

describe('TrainsService', () => {
  let service: TrainsService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [TrainsService, PrismaService, ExercisesService],
    }).compile();

    service = module.get<TrainsService>(TrainsService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
