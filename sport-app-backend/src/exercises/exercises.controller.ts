import { ExercisesService } from './exercises.service';
import {
  Body,
  Controller,
  Delete,
  Get,
  HttpCode,
  HttpStatus,
  Param,
  ParseIntPipe,
  Patch,
  Post,
  Query,
  UploadedFile,
  UploadedFiles,
  UseGuards,
  UseInterceptors,
} from '@nestjs/common';
import { CreateExerciseDto, UpdateExerciseDto } from './exercises.dto';
import { User } from 'src/users/user.annotation';
import { JwtAuthGuard } from 'src/auth/authguard/jwt-auth.guard';
import {
  FileFieldsInterceptor,
  FileInterceptor,
  FilesInterceptor,
} from '@nestjs/platform-express';
import { ImagePipe } from 'src/files/pipes/image.pipe';
import { imageInterceptor } from './exercises.files.interceptor';
import { UpdateExercisePipe } from './pipes/update.exercises.pipe';
import { exercisePage } from './constant';
import { MuscleGroup, Prisma} from '@prisma/client';
import { MuscleGroupPipe } from './pipes/muscle.group.pipe';
import { CreateExercisePipe } from './pipes/create.exercises.pipe';
import { UserDTO } from 'src/users/users.dto';

class GetParam {
  skip?: number;
  take?: number;
  cursor?: Prisma.ExerciseWhereUniqueInput;
  where?: Prisma.ExerciseWhereInput;
  orderBy?: Prisma.ExerciseOrderByWithRelationInput;
}

@UseGuards(JwtAuthGuard)
@Controller('api/exercises')
export class ExercisesController {
  constructor(private exercisesService: ExercisesService) {}

  @Post()
  @HttpCode(HttpStatus.CREATED)
  @UseInterceptors(
    FileInterceptor(imageInterceptor.name, {
      storage: imageInterceptor.storage,
      fileFilter: imageInterceptor.fileFilter,
    }),
  )
  async create(
    @Body(new CreateExercisePipe({ optional: true }))
    exercise: CreateExerciseDto,
    @User() user,
    @UploadedFile(ImagePipe) image?: Express.Multer.File,
  ) {
    if (image) {
      exercise.image = image.path;
    }

    const data = {
      ...exercise,
      author: {
        connect: {
          id: user.userId,
        },
      },
    };
    return await this.exercisesService.create(data);
  }

  @Get('count/')
  async count(@User() user : UserDTO){
   return await this.exercisesService.countGroupBy(user);
  }

  @Get(':id')
  async getById(@Param('id', ParseIntPipe) id: number, @User() user : UserDTO) {
    return await this.exercisesService.findById(id, user);
  }

  @Get('train/:id')
  async getByTrain(@Param('id', ParseIntPipe) id: number) {
    return await this.exercisesService.findByTrainId(id);
  }

  @Get()
  async getMany(@User() user : UserDTO,
    @Query('page', new ParseIntPipe({ optional: true })) page?: number,
    @Query('muscleGroup', new MuscleGroupPipe({ optional: true }))
    muscleGroup?: MuscleGroup) {
    let param: GetParam = {};
    if (page) {
      param.skip = exercisePage.size * (page - 1);
      param.take = exercisePage.size;
    }

    param.where = {
      author_id : user.userId
    };
    
    if (muscleGroup) {
      param.where = {
        muscleGroup: muscleGroup,
        author_id : user.userId
      };
    }

    param.orderBy = {
      createdAt : 'asc'
    }
    
    
    //param.where.author_id = user.userId;

    return await this.exercisesService.exercises(param);
  }

  @Patch()
  @UseInterceptors(
    FileInterceptor(imageInterceptor.name, {
      storage: imageInterceptor.storage,
      fileFilter: imageInterceptor.fileFilter,
    }),
  )
  async update(
    @Body(new UpdateExercisePipe({ optional: true }))
    exercise: UpdateExerciseDto,
    @User() user,
    @UploadedFile(ImagePipe) image?: Express.Multer.File,
  ) {
    exercise.author_id = user.userId;
    if (image) {
      exercise.image = image.path;
    }

    return await this.exercisesService.update({
      where: { id: exercise.id },
      data: exercise,
    });
  }

  @Delete(':id')
  async delete(@Param('id', ParseIntPipe) id, @User() user) {
    return await this.exercisesService.delete({ id: id }, user.userId);
  }
}
