import { Injectable, OnModuleInit } from '@nestjs/common';
import { PrismaClient } from '@prisma/client';
import { prismaLoggingMiddleware } from './prisma.logging.middleware';

@Injectable()
export class PrismaService extends PrismaClient implements OnModuleInit {

  constructor() {
    super();
    this.$use(prismaLoggingMiddleware());
  }

  async onModuleInit() {
    await this.$connect();
  }
}