import { Test, TestingModule } from '@nestjs/testing';
import { BodyReactionsService } from './bodyreactions.service';

describe('BodyreactionsService', () => {
  let service: BodyReactionsService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [BodyReactionsService],
    }).compile();

    service = module.get<BodyReactionsService>(BodyReactionsService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
