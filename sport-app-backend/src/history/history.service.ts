import { PrismaService } from 'src/prisma/prisma.service';
import { Injectable } from '@nestjs/common';
import { Prisma, History } from '@prisma/client';
import { historyPage } from './constant';

@Injectable()
export class HistoryService {
  constructor(private prisma: PrismaService) {}

  async history(
    where: Prisma.HistoryWhereUniqueInput,
  ): Promise<History | null> {
    return this.prisma.history.findUnique({
      where,
    });
  }

  async histories(params: {
    skip?: number;
    take?: number;
    cursor?: Prisma.HistoryWhereUniqueInput;
    where?: Prisma.HistoryWhereInput;
    orderBy?: Prisma.HistoryOrderByWithRelationInput;
  }): Promise<History[]> {
    const { skip, take, cursor, where, orderBy } = params;
    return this.prisma.history.findMany({
      skip,
      take,
      cursor,
      where,
      orderBy,
    });
  }

  async historiesByPage(params: {
    page: number;
    cursor?: Prisma.HistoryWhereUniqueInput;
    where?: Prisma.HistoryWhereInput;
    orderBy?: Prisma.HistoryOrderByWithRelationInput;
  }): Promise<History[]> {
    const { page, cursor, where, orderBy } = params;
    const skip = historyPage.size * (page - 1);
    const take = historyPage.size;
    return this.histories({ skip, take, cursor, where, orderBy });
  }

  async create(data: Prisma.HistoryCreateInput): Promise<History> {
    return this.prisma.history.create({
      data,
    });
  }

  async update(params: {
    where: Prisma.HistoryWhereUniqueInput;
    data: Prisma.HistoryUpdateInput;
  }): Promise<History> {
    const { where, data } = params;
    return this.prisma.history.update({
      data,
      where,
    });
  }

  async delete(where: Prisma.HistoryWhereUniqueInput): Promise<History> {
    return this.prisma.history.delete({
      where,
    });
  }
}
