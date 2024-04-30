import { Test, TestingModule } from '@nestjs/testing';
import { RemindsController } from './reminds.controller';

describe('RemindsController', () => {
  let controller: RemindsController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [RemindsController],
    }).compile();

    controller = module.get<RemindsController>(RemindsController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
