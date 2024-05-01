import {
  PipeTransform,
  Injectable,
  ArgumentMetadata,
  BadRequestException,
  ParseIntPipe,
} from '@nestjs/common';
import { validate } from 'class-validator';
import { plainToClass } from 'class-transformer';
import { UpdateExerciseDto } from '../exercises.dto';
import { parseMuscleGroup } from '../utils/parse.muscle.group';

interface UpdateExercisePipeOptions {
  optional?: boolean;
}

@Injectable()
export class UpdateExercisePipe implements PipeTransform<any> {
  constructor(private readonly options: UpdateExercisePipeOptions = {}) {}

  async transform(value: any, { metatype }: ArgumentMetadata) {
    if (this.options.optional  && !value) {
      return null;
    }

    console.log(value);
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

      if (!value.hasOwnProperty('id')) {
        throw Error('field id is not found');
      }

      value.id = parseInt(value.id)

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
      UpdateExerciseDto,
    ];
    console.log(types.includes(metatype));
    return !types.includes(metatype);
  }
}
