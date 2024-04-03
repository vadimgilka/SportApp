import { Test, TestingModule } from '@nestjs/testing';
import { HistoryService } from './history.service';
import { Prisma } from '@prisma/client';
import { PrismaService } from 'nestjs-prisma';

describe('HistoryService', () => {
  let service: HistoryService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [HistoryService, PrismaService],
    }).compile();

    service = module.get<HistoryService>(HistoryService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
