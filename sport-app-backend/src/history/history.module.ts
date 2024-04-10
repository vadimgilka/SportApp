import { Module } from '@nestjs/common';
import { PrismaModule } from 'src/prisma/prisma.module';
import { HistoryService } from './history.service';
import { HistoryController } from './history.controller';
import { PrismaService } from 'src/prisma/prisma.service';
@Module({
    imports : [],
    providers: [PrismaService,HistoryService],
    controllers: [HistoryController],
    exports: [HistoryService]
  })
export class HistoryModule {}
