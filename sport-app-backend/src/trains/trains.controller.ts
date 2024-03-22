import { Exercise } from '@prisma/client';
import {Post, Body, Controller, UseGuards, ParseIntPipe, Param, Get, Delete, Patch } from '@nestjs/common';
import { JwtAuthGuard } from 'src/auth/authguard/jwt-auth.guard';
import { CreateTrainDto, UpdateTrainDto } from './trains.dto';
import { User } from 'src/users/user.annotation';
import { TrainsService } from './trains.service';
import { use } from 'passport';

@UseGuards(JwtAuthGuard)
@Controller('api/trains')
export class TrainsController {

    constructor(private trainService :TrainsService) {}

    @Post()
    async create(@Body() train : CreateTrainDto, @User() user){
        train.author_id = user.userId;
        return await this.trainService.create(train);
    }

    @Get(':id')
    async getById(@Param('id', ParseIntPipe) id: number) {
      return await this.trainService.findById(id);
    }

    @Delete(':id')
    async delete(@Param('id', ParseIntPipe) id: number, @User() user){
        return await this.trainService.delete({id : id}, user.userId);
    }

    @Patch(':id')
    async update(@Body() train : UpdateTrainDto, @User() user){
      train.author_id = user.userId;
      return await this.trainService.update({where :{id : train.id}, data : train })
    }
}
