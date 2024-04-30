import { Exercise, Prisma } from '@prisma/client';
import {Post, Body, Controller, UseGuards, ParseIntPipe, Param, Get, Delete, Put, HttpCode, HttpStatus, Query } from '@nestjs/common';
import { JwtAuthGuard } from 'src/auth/authguard/jwt-auth.guard';
import { CreateTrainDto, UpdateTrainDto } from './trains.dto';
import { User } from 'src/users/user.annotation';
import { TrainsService } from './trains.service';
import { trainPage } from './constant';

class GetParam {
  skip?: number;
  take?: number;
  cursor?: Prisma.TrainWhereUniqueInput;
  where?: Prisma.TrainWhereInput;
  orderBy?: Prisma.TrainOrderByWithRelationInput;
}

@UseGuards(JwtAuthGuard)
@Controller('api/trains')
export class TrainsController {

    constructor(private trainService :TrainsService) {}

    @Post()
    @HttpCode(HttpStatus.CREATED)
    async create(@Body() train : CreateTrainDto, @User() user){
        train.author_id = user.userId;
        return await this.trainService.create(train);
    }

    @Get(':id')
    async getById(@Param('id', ParseIntPipe) id: number, @User() user) {
      return await this.trainService.findById(id, user);
    }

    @Delete(':id')
    async delete(@Param('id', ParseIntPipe) id: number, @User() user){
        return await this.trainService.delete({id : id}, user.userId);
    }

    @Put()
    async update(@Body() train : UpdateTrainDto, @User() user){
      train.author_id = user.userId;
      return await this.trainService.update({where :{id : train.id}, data : train })
    }

    @Get()
    async getMany(@User() user, @Query('page', new ParseIntPipe({ optional: true })) page?: number){
      
      const param : GetParam = {}
      param.where = {author_id : user.userId};
      if(page){
        param.skip = trainPage.size * (page - 1);
        param.take = trainPage.size;
      }

      return await this.trainService.trains(param);
    }
}
