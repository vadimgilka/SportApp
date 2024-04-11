import {
  PipeTransform,
  Injectable,
  ArgumentMetadata,
  BadRequestException,
} from '@nestjs/common';
import { validate } from 'class-validator';
import { plainToClass } from 'class-transformer';
import { CreateExerciseDto, UpdateExerciseDto } from '../exercises.dto';
import { MuscleGroupPipe } from './muscle.group.pipe';
import { parseMuscleGroup } from '../utils/parse.muscle.group';

interface CreateExercisePipeOptions {
  optional?: boolean;
}

@Injectable()
export class UpdateExercisePipe implements PipeTransform<any> {
  constructor(private readonly options: CreateExercisePipeOptions = {}) {}

  async transform(value: any, { metatype }: ArgumentMetadata) {

    if(!this.options.optional){
        return null;
    }

    if (!metatype || this.toValidate(metatype)) {
      return value;
    }

    const obj = plainToClass(metatype, value);
    const errors = await validate(obj);

    if (errors.length > 0) {
      throw new BadRequestException('Валидация провалилась');
    }

    try {
      if (value.hasOwnProperty('muscleGroup')) {
        value.muscleGroup = parseMuscleGroup(value.muscleGroup);
      }
    } catch (e) {
      throw new BadRequestException(e.message);
    }
    return value;
  }

  private toValidate(metatype: Function): boolean {
    const types: Function[] = [
      String,
      Boolean,
      Number,
      Array,
      Object,
      CreateExerciseDto,
    ];
    console.log(types.includes(metatype));
    return !types.includes(metatype);
  }
}
