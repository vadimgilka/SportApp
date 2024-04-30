import { Test, TestingModule } from '@nestjs/testing';
import { BiologicalAdditivesService } from './biological-additives.service';

describe('BiologicalAdditivesService', () => {
  let service: BiologicalAdditivesService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [BiologicalAdditivesService],
    }).compile();

    service = module.get<BiologicalAdditivesService>(BiologicalAdditivesService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
