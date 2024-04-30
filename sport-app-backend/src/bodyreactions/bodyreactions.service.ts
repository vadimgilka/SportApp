import { Injectable } from '@nestjs/common';
import { BodyReaction, Prisma } from '@prisma/client';
import { connect } from 'http2';
import { use } from 'passport';
import { PrismaService } from 'src/prisma/prisma.service';
import { UserDTO } from 'src/users/users.dto';

@Injectable()
export class BodyReactionsService {
  constructor(private prisma: PrismaService) {}

  async reaction(
    where: Prisma.BodyReactionWhereUniqueInput,
  ): Promise<BodyReaction | null> {
    return this.prisma.bodyReaction.findUnique({
      where,
    });
  }

  async reactions(params: {
    skip?: number;
    take?: number;
    cursor?: Prisma.BodyReactionWhereUniqueInput;
    where?: Prisma.BodyReactionWhereInput;
    orderBy?: Prisma.BodyReactionOrderByWithRelationInput;
  }): Promise<BodyReaction[]> {
    const { skip, take, cursor, where, orderBy } = params;
    return this.prisma.bodyReaction.findMany({
      skip,
      take,
      cursor,
      where,
      orderBy,
    });
  }

  async create(data: Prisma.BodyReactionCreateInput, user): Promise<BodyReaction> {

    data.User ={
        connect: {
            id : user.userId
        }
    };

    return this.prisma.bodyReaction.create({
      data,
    });
  }

  async update(params: {
    where: Prisma.BodyReactionWhereUniqueInput;
    data: Prisma.BodyReactionUpdateInput;
  }, user : UserDTO): Promise<BodyReaction> {
    const { where, data } = params;
    where.author_id = user.userId;
    return this.prisma.bodyReaction.update({
      data,
      where,
    });
  }

  async delete(
    where: Prisma.BodyReactionWhereUniqueInput,
  ): Promise<BodyReaction> {
    return this.prisma.bodyReaction.delete({
      where,
    });
  }
}
