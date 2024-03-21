import { Exercise } from '@prisma/client';
import {Post, Body, Controller, UseGuards } from '@nestjs/common';
import { JwtAuthGuard } from 'src/auth/authguard/jwt-auth.guard';
import { CreateTrainDto } from './trains.dto';
import { User } from 'src/users/user.annotation';
import { TrainsService } from './trains.service';
import { use } from 'passport';

@UseGuards(JwtAuthGuard)
@Controller('trains')
export class TrainsController {

    constructor(private trainService :TrainsService) {}

    @Post()
    async create(@Body() train : CreateTrainDto, @User() user){
        train.author_id = user.userId;
        return await this.trainService.create(train);
    }
}
