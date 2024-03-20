import { ExercisesService } from './exercises.service';
import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  ParseIntPipe,
  Patch,
  Post,
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
import {
  imageInterceptor,
} from './exercises.files.interceptor';
import { UpdateExercisePipe } from './pipes/update.exercises.pipe';

@UseGuards(JwtAuthGuard)
@Controller('api/exercises')
export class ExercisesController {
  constructor(private exercisesService: ExercisesService) {}

  @Post()
  @UseInterceptors(
    FileInterceptor(imageInterceptor.name, { storage: imageInterceptor.storage, fileFilter: imageInterceptor.fileFilter }),
  )
  async create(
    @Body() exercise: CreateExerciseDto,
    @User() user,
    @UploadedFile(ImagePipe) image?: Express.Multer.File,
  ) {
    console.log(image);
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

  @Get(':id')
  async getById(@Param('id', ParseIntPipe) id: number) {
    return await this.exercisesService.findById(id);
  }

  @Patch()
  @UseInterceptors(
    FileInterceptor(imageInterceptor.name, { storage: imageInterceptor.storage, fileFilter: imageInterceptor.fileFilter }),
  )
  async update(
    @Body(UpdateExercisePipe) exercise: UpdateExerciseDto,
    @User() user,
    @UploadedFile(ImagePipe) image?: Express.Multer.File,
  ) {
    console.log(exercise)
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
  async del(@Param('id', ParseIntPipe) id, @User() user) {
    return await this.exercisesService.delete({ id: id }, user.userId);
  }
}
