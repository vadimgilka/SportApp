import { ExercisesService } from './exercises.service';
import { Body, Controller, Delete, Get, Param, ParseIntPipe, Patch, Post, UseGuards } from '@nestjs/common';
import { CreateExerciseDto, UpdateExerciseDto } from './exercises.dto';
import { User } from 'src/users/user.annotation';
import { JwtAuthGuard } from 'src/auth/authguard/jwt-auth.guard';

@UseGuards(JwtAuthGuard)
@Controller('api/exercises')
export class ExercisesController {
  constructor(private exercisesService: ExercisesService) {}

  @Post()
  async create(@Body() exercise: CreateExerciseDto, @User() user) {
    const data = {
      ...exercise,
      author: {
        connect: {
          id: user.userId,
        },
      },
    };
    return await this.exercisesService.createExercise(data);
  }

  @Get()
  async getById(@Param('id', ParseIntPipe) id : number){
    return await this.exercisesService.findById(id);
  }

  @Patch()
  async update(@Body() exercise: UpdateExerciseDto, @User() user){
    exercise.author_id = user.userId;
    return await this.exercisesService.updateExercise({where: {id : exercise.id}, data : exercise});
  }

  @Delete()
  async del(@Param('id', ParseIntPipe) id){
    return await this.exercisesService.deleteExercise({id : id});
  }
}
