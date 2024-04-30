import { Module } from '@nestjs/common';
import { PrismaService } from 'src/prisma/prisma.service';
import { BiologicalAdditivesService } from './biological-additives.service';
import { BiologicalAdditivesController } from './biological-additives.controller';

@Module({  imports: [],
    providers: [PrismaService, BiologicalAdditivesService],
    controllers: [BiologicalAdditivesController],
    exports: [BiologicalAdditivesService],})
export class BiologicalAdditivesModule {}
