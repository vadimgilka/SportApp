import { Module } from '@nestjs/common';
import { PrismaService } from 'src/prisma/prisma.service';
import { RemindsController } from './reminds.controller';
import { RemindsService } from './reminds.service';
import { FcmNotificationService } from 'src/fcm-notification/fcm-notification.service';

@Module({
  providers: [PrismaService, RemindsService, FcmNotificationService],
  controllers: [RemindsController],
  exports: [RemindsService],
})
export class RemindsModule {}
