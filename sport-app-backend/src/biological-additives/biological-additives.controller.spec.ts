import { Test, TestingModule } from '@nestjs/testing';
import { BiologicalAdditivesController } from './biological-additives.controller';

describe('BiologicalAdditivesController', () => {
  let controller: BiologicalAdditivesController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [BiologicalAdditivesController],
    }).compile();

    controller = module.get<BiologicalAdditivesController>(BiologicalAdditivesController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
