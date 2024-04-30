import { Test, TestingModule } from '@nestjs/testing';
import { RemindsService } from './reminds.service';

describe('RemindsService', () => {
  let service: RemindsService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [RemindsService],
    }).compile();

    service = module.get<RemindsService>(RemindsService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
