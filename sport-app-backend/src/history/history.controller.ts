import { HistoryService } from './history.service';
import { Body, Controller, Get, HttpCode, HttpStatus, ParseIntPipe, Post, Query, UseGuards } from '@nestjs/common';
import { HistoryDto } from './history.dto';
import { User } from 'src/users/user.annotation';
import { JwtAuthGuard } from 'src/auth/authguard/jwt-auth.guard';

@UseGuards(JwtAuthGuard)
@Controller('api/history')
export class HistoryController {

    constructor(private historyService : HistoryService) {}

    @Post()
    @HttpCode(HttpStatus.CREATED)
    async create(@Body() history : HistoryDto, @User() user) {
        history.authorId = user.userId;
        return await this.historyService.create(history);
    }


    @Get()
    async getMany(@Query('page', ParseIntPipe) page : number, @User() user) {
        const where = {
            authorId : user.userId
        };

        return await this.historyService.historiesByPage({page, where, orderBy : {createdAt : 'asc'}})
    }
}
