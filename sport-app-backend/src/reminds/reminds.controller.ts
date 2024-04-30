import { Body, Controller, Delete, Get, HttpCode, HttpStatus, Param, ParseIntPipe, Post, Put, UseGuards } from '@nestjs/common';
import { Prisma } from '@prisma/client';
import { User } from 'src/users/user.annotation';
import { UserDTO } from 'src/users/users.dto';
import { RemindsService } from './reminds.service';
import { CreateRemindDTO, UpdateRemindDTO } from './reminds.dto';
import { JwtAuthGuard } from 'src/auth/authguard/jwt-auth.guard';

@UseGuards(JwtAuthGuard)
@Controller('api/reminds')
export class RemindsController {
    constructor(private remindService : RemindsService) {}

    @Post()
    @HttpCode(HttpStatus.CREATED)
    async create(@Body() reaction : CreateRemindDTO, @User() user : UserDTO){
        console.log(reaction);
        const bioId = reaction.biologicalAdditiveId;
        delete reaction.biologicalAdditiveId;
        return await this.remindService.create(reaction, user, bioId);
    }

    @Get(':id')
    async getById(@Param('id', ParseIntPipe) id: number, @User() user : UserDTO){
        const where : Prisma.RemindWhereUniqueInput = {id : id, userId : user.userId};
        return await this.remindService.remind(where);
    }

    @Get()
    async getMany(@User() user : UserDTO){
        const where : Prisma.RemindWhereInput = {userId : user.userId};
        return await this.remindService.reminds({where});
    }

    @Put()
    async update(@Body() data : UpdateRemindDTO, @User() user : UserDTO){
        const where : Prisma.RemindWhereUniqueInput = {id : data.id};
        return await this.remindService.update({data, where}, user);
    }

    @Delete(':id')
    async delete(@Param('id', ParseIntPipe) id: number, @User() user : UserDTO){
        const where : Prisma.RemindWhereUniqueInput = {id : id, userId : user.userId};
        return await this.remindService.delete(where);
    }
}
