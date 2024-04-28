import { Injectable } from '@nestjs/common';
import { Prisma, Remind } from '@prisma/client';
import { PrismaService } from 'src/prisma/prisma.service';
import { UserDTO } from 'src/users/users.dto';

@Injectable()
export class RemindsService {
    constructor(private prisma: PrismaService) {}

    async remind(
        where: Prisma.RemindWhereUniqueInput,
      ): Promise<Remind | null> {
        return this.prisma.remind.findUnique({
          where,
        });
      }
    
      async reminds(params: {
        skip?: number;
        take?: number;
        cursor?: Prisma.RemindWhereUniqueInput;
        where?: Prisma.RemindWhereInput;
        orderBy?: Prisma.RemindOrderByWithRelationInput;
      }): Promise<Remind[]> {
        const { skip, take, cursor, where, orderBy } = params;
        return this.prisma.remind.findMany({
          skip,
          take,
          cursor,
          where,
          orderBy,
        });
      }
    
      async create(data: Prisma.RemindCreateInput, user : UserDTO, biologicalAdditiveId : number): Promise<Remind> {
    
        data.User ={
            connect: {
                id : user.userId
            }
        };

        data.BiologicalAdditive = {
            connect : {
                id : biologicalAdditiveId
            }
        }
        console.log(user)
        console.log(data)
        return this.prisma.remind.create({
          data,
        });
      }
    
      async update(params: {
        where: Prisma.RemindWhereUniqueInput;
        data: Prisma.RemindUpdateInput;
      }, user : UserDTO): Promise<Remind> {
        const { where, data } = params;
        where.userId = user.userId;
        return this.prisma.remind.update({
          data,
          where,
        });
      }
    
      async delete(
        where: Prisma.RemindWhereUniqueInput,
      ): Promise<Remind> {
        return this.prisma.remind.delete({
          where,
        });
      }
}
