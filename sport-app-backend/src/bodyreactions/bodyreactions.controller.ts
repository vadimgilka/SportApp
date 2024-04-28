import { BodyReactionsService } from './bodyreactions.service';
import { Body, Controller, Get, HttpCode, HttpStatus, Param, ParseIntPipe, Post, UseGuards, Delete, Put } from '@nestjs/common';
import { JwtAuthGuard } from 'src/auth/authguard/jwt-auth.guard';
import { CreateBodyReactionDTO, UpdateBodyReactionDTO } from './bodyreactions.dto';
import { User } from 'src/users/user.annotation';
import { Prisma} from '@prisma/client';
import { UserDTO } from 'src/users/users.dto';

@UseGuards(JwtAuthGuard)
@Controller('api/bodyreaction')
export class BodyReactionsController {

    constructor(private bodyReactionsService : BodyReactionsService) {}

    @Post()
    @HttpCode(HttpStatus.CREATED)
    async create(@Body() reaction : CreateBodyReactionDTO, @User() user : UserDTO){
        return await this.bodyReactionsService.create(reaction, user);
    }

    @Get(':id')
    async getById(@Param('id', ParseIntPipe) id: number, @User() user : UserDTO){
        const where : Prisma.BodyReactionWhereUniqueInput = {id : id, author_id : user.userId};
        return await this.bodyReactionsService.reaction(where);
    }

    @Get()
    async getMany(@User() user : UserDTO){
        const where : Prisma.BodyReactionWhereInput = {author_id : user.userId};
        return await this.bodyReactionsService.reactions({where});
    }

    @Put()
    async update(@Body() data : UpdateBodyReactionDTO, @User() user : UserDTO){
        const where : Prisma.BodyReactionWhereUniqueInput = {id : data.id};
        return await this.bodyReactionsService.update({data, where}, user);
    }

    @Delete(':id')
    async delete(@Param('id', ParseIntPipe) id: number, @User() user : UserDTO){
        const where : Prisma.BodyReactionWhereUniqueInput = {id : id, author_id : user.userId};
        return await this.bodyReactionsService.delete(where);
    }
}
