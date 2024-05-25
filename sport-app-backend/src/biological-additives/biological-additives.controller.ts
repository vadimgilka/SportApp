import { Body, Controller, Delete, Get, HttpCode, HttpStatus, Param, ParseIntPipe, Post, Put, UseGuards } from '@nestjs/common';
import { Prisma } from '@prisma/client';
import { JwtAuthGuard } from 'src/auth/authguard/jwt-auth.guard';
import { User } from 'src/users/user.annotation';
import { UserDTO } from 'src/users/users.dto';
import { BiologicalAdditivesService } from './biological-additives.service';
import { CreateBiologicalAdditiveDTO, CreateBiologicalAdditiveWithRemindsDTO, UpdateBiologicalAdditiveDTO, UpdateBiologicalAdditiveWithRemindsDTO } from './biological-additives.dto';


@UseGuards(JwtAuthGuard)
@Controller('api/biological-additives')
export class BiologicalAdditivesController {
    constructor(private bioService : BiologicalAdditivesService) {}

    @Post()
    @HttpCode(HttpStatus.CREATED)
    async create(@Body() reaction : CreateBiologicalAdditiveDTO, @User() user : UserDTO){
        return await this.bioService.create(reaction, user);
    }

    @Post("/reminds")
    @HttpCode(HttpStatus.CREATED)
    async createWithReminds(@Body() reaction : CreateBiologicalAdditiveWithRemindsDTO, @User() user : UserDTO){
        return await this.bioService.createWithReminds(reaction, user);
    }

    @Get(':id')
    async getById(@Param('id', ParseIntPipe) id: number, @User() user : UserDTO){
        const where : Prisma.BiologicalAdditiveWhereUniqueInput = {id : id, author_id : user.userId};
        return await this.bioService.additive(where);
    }

    @Get()
    async getMany(@User() user : UserDTO){
        const where : Prisma.BiologicalAdditiveWhereInput = {author_id : user.userId};
        return await this.bioService.additives({where, orderBy : {createdAt: 'asc'}});
    }

    @Put()
    async update(@Body() data : UpdateBiologicalAdditiveDTO, @User() user : UserDTO){
        const where : Prisma.BiologicalAdditiveWhereUniqueInput = {id : data.id};
        return await this.bioService.update({data, where}, user);
    }

    @Put("/reminds")
    async updateWithReminds(@Body() data : UpdateBiologicalAdditiveWithRemindsDTO, @User() user : UserDTO){
        const where : Prisma.BiologicalAdditiveWhereUniqueInput = {id : data.id};
        return await this.bioService.updateWithReminds(data, user)
    }

    @Delete(':id')
    async delete(@Param('id', ParseIntPipe) id: number, @User() user : UserDTO){
        const where : Prisma.BiologicalAdditiveWhereUniqueInput = {id : id, author_id : user.userId};
        return await this.bioService.delete(where);
    }
}
