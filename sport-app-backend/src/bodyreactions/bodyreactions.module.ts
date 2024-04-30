import { Module } from '@nestjs/common';
import { BodyReactionsService } from './bodyreactions.service';
import { BodyReactionsController } from './bodyreactions.controller';
import { PrismaService } from 'src/prisma/prisma.service';

@Module({
  imports: [],
  providers: [PrismaService, BodyReactionsService],
  controllers: [BodyReactionsController],
  exports: [BodyReactionsService],
})
export class BodyReactionsModule {}
