import { Injectable } from '@nestjs/common';
import { BiologicalAdditive, Prisma, User } from '@prisma/client';
import { PrismaService } from 'src/prisma/prisma.service';
import { UserDTO } from 'src/users/users.dto';
import {
  CreateBiologicalAdditiveWithRemindsDTO,
  UpdateBiologicalAdditiveWithRemindsDTO,
} from './biological-additives.dto';
import { CreateBioAdditiveRemindDTO } from 'src/reminds/reminds.dto';

@Injectable()
export class BiologicalAdditivesService {
  constructor(private prisma: PrismaService) {}

  async additive(
    where: Prisma.BiologicalAdditiveWhereUniqueInput,
  ): Promise<BiologicalAdditive | null> {
    return this.prisma.biologicalAdditive.findUnique({
      where,
      include: {
        reminds: true,
      },
    });
  }

  async additives(params: {
    skip?: number;
    take?: number;
    cursor?: Prisma.BiologicalAdditiveWhereUniqueInput;
    where?: Prisma.BiologicalAdditiveWhereInput;
    orderBy?: Prisma.BiologicalAdditiveOrderByWithRelationInput;
  }): Promise<BiologicalAdditive[]> {
    const { skip, take, cursor, where, orderBy } = params;
    return this.prisma.biologicalAdditive.findMany({
      skip,
      take,
      cursor,
      where,
      orderBy,
      include: {
        reminds: true,
      },
    });
  }

  async create(
    data: Prisma.BiologicalAdditiveCreateInput,
    user,
  ): Promise<BiologicalAdditive> {
    data.User = {
      connect: {
        id: user.userId,
      },
    };

    return this.prisma.biologicalAdditive.create({
      data,
    });
  }

  async update(
    params: {
      where: Prisma.BiologicalAdditiveWhereUniqueInput;
      data: Prisma.BiologicalAdditiveUpdateInput;
    },
    user: UserDTO,
  ): Promise<BiologicalAdditive> {
    const { where, data } = params;
    where.author_id = user.userId;
    return this.prisma.biologicalAdditive.update({
      data,
      where,
    });
  }

  async delete(
    where: Prisma.BiologicalAdditiveWhereUniqueInput,
  ): Promise<BiologicalAdditive> {
    return this.prisma.biologicalAdditive.delete({
      where,
    });
  }

  async createWithReminds(
    data: CreateBiologicalAdditiveWithRemindsDTO,
    user: UserDTO,
  ) {
    data.author_id = user.userId;
    console.log(data instanceof CreateBiologicalAdditiveWithRemindsDTO)
    const reminds: Prisma.RemindCreateInput[] = data.reminds.map((x : CreateBioAdditiveRemindDTO) => {
      console.log(x instanceof CreateBioAdditiveRemindDTO)
      console.log(x)
      return CreateBioAdditiveRemindDTO.toCreateInput(x, user.userId);
    });
    const createInput: Prisma.BiologicalAdditiveCreateInput = {
      ...CreateBiologicalAdditiveWithRemindsDTO.toCreateInput(data),
      reminds: {
        create: reminds,
      },
    };

    return this.prisma.biologicalAdditive.create({
      data: createInput,
      include: { reminds: true },
    });
  }

  async updateWithReminds(
    data: UpdateBiologicalAdditiveWithRemindsDTO,
    user: UserDTO,
  ) {
    data.author_id = user.userId;
    const reminds: Prisma.RemindCreateInput[] = data.reminds.map((x) => 
      CreateBioAdditiveRemindDTO.toCreateInput(x, user.userId)
    );
    const updateInput: Prisma.BiologicalAdditiveUpdateInput = {
      ...UpdateBiologicalAdditiveWithRemindsDTO.toUpdateInput(data),
      reminds: {
        create: reminds,
      },
    };

    return this.prisma.biologicalAdditive.update({
      where : {
        id : data.id
      },
      data: updateInput,
      include: { reminds: true },
    });
  }
}
