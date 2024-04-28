import { Module } from '@nestjs/common';
import { PrismaService } from 'src/prisma/prisma.service';
import { RemindsController } from './reminds.controller';
import { RemindsService } from './reminds.service';

@Module({
  providers: [PrismaService, RemindsService],
  controllers: [RemindsController],
  exports: [RemindsService],
})
export class RemindsModule {}
