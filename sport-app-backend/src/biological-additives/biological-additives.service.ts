import { Injectable } from '@nestjs/common';
import { BiologicalAdditive, Prisma } from '@prisma/client';
import { PrismaService } from 'src/prisma/prisma.service';
import { UserDTO } from 'src/users/users.dto';

@Injectable()
export class BiologicalAdditivesService {
    constructor(private prisma: PrismaService) {}

    async additive(
        where: Prisma.BiologicalAdditiveWhereUniqueInput,
      ): Promise<BiologicalAdditive | null> {
        return this.prisma.biologicalAdditive.findUnique({
          where,
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
        });
      }
    
      async create(data: Prisma.BiologicalAdditiveCreateInput, user): Promise<BiologicalAdditive> {
    
        data.User ={
            connect: {
                id : user.userId
            }
        };
    
        return this.prisma.biologicalAdditive.create({
          data,
        });
      }
    
      async update(params: {
        where: Prisma.BiologicalAdditiveWhereUniqueInput;
        data: Prisma.BiologicalAdditiveUpdateInput;
      }, user : UserDTO): Promise<BiologicalAdditive> {
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
}
