import { Test, TestingModule } from '@nestjs/testing';
import { BodyReactionsController } from './bodyreactions.controller';

describe('BodyreactionsController', () => {
  let controller: BodyReactionsController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [BodyReactionsController],
    }).compile();

    controller = module.get<BodyReactionsController>(BodyReactionsController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
