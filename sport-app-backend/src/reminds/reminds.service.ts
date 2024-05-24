import { Injectable, BadRequestException } from '@nestjs/common';
import { Cron } from '@nestjs/schedule';
import { Prisma, Remind, BiologicalAdditive } from '@prisma/client';
import { isNumber } from 'lodash';
import { FcmNotificationService } from 'src/fcm-notification/fcm-notification.service';
import { PayloadFCM } from 'src/fcm-notification/payload';
import logger from 'src/prisma/logger';
import { PrismaService } from 'src/prisma/prisma.service';
import { UserDTO } from 'src/users/users.dto';

@Injectable()
export class RemindsService {
  constructor(
    private prisma: PrismaService,
    private fcm: FcmNotificationService,
  ) {}

  async remind(where: Prisma.RemindWhereUniqueInput): Promise<Remind | null> {
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
    include?: Prisma.RemindInclude;
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

  async create(
    data: Prisma.RemindCreateInput,
    user: UserDTO,
    biologicalAdditiveId: number,
  ): Promise<Remind> {
    data.User = {
      connect: {
        id: user.userId,
      },
    };

    data.BiologicalAdditive = {
      connect: {
        id: biologicalAdditiveId,
      },
    };
    console.log(user);
    console.log(data);

    const time = data.time;

    if (!time || time < 0 || time > 1440) {
      throw new BadRequestException(
        'incorrect time value. Time must be between 0 and 1440',
      );
    }

    return this.prisma.remind.create({
      data,
    });
  }

  async update(
    params: {
      where: Prisma.RemindWhereUniqueInput;
      data: Prisma.RemindUpdateInput;
    },
    user: UserDTO,
  ): Promise<Remind> {
    const { where, data } = params;
    where.userId = user.userId;

    const time = data.time;

    if (isNumber(time) && (time < 0 || time > 1440)) {
      throw new BadRequestException(
        'incorrect time value. Time must be between 0 and 1440',
      );
    }

    return this.prisma.remind.update({
      data,
      where,
    });
  }

  async delete(where: Prisma.RemindWhereUniqueInput): Promise<Remind> {
    return this.prisma.remind.delete({
      where,
    });
  }

  @Cron('*/1 * * * *')
  async sendReminder() {
    const time = this.getTime();
    const reminds = await this.reminds({
      where: { time },
      // include : {
      //   BiologicalAdditive : true
      // }
    });

    for (const remind of reminds) {
      logger.info(remind);
      if (remind.token) {
        await this.fcm.sendMessage(this.remindToPayloadFCM(remind));
      }
    }
  }

  private getTime() {
    const currentTime = new Date();
    const hours = currentTime.getUTCHours();
    const minutes = currentTime.getUTCMinutes();

    console.log(hours + ':' + minutes);

    return hours * 60 + minutes;
  }

  private remindToPayloadFCM(remind: Remind): PayloadFCM {
    const payload: PayloadFCM = {
      token: remind.token,
      notification: {
        title: 'время пить таблетки',
        body: 'твоя дозировка =  ' + remind.measure,
      },
      data: {
        id: String(remind.id),
        time: String(remind.time),
        measure: String(remind.measure),
      },
    };
    
    return payload;
  }
}
