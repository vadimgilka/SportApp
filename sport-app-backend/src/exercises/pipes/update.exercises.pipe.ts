import { PipeTransform, Injectable, ArgumentMetadata, BadRequestException } from '@nestjs/common'
import { validate } from 'class-validator'
import { plainToClass } from 'class-transformer'
import { UpdateExerciseDto } from '../exercises.dto';

@Injectable()
export class UpdateExercisePipe implements PipeTransform<any> {
  async transform(value: any, { metatype }: ArgumentMetadata) {
    console.log('value =' + value);
    console.log(metatype);
    if (!metatype || this.toValidate(metatype)) {
      return value
    }

    const obj = plainToClass(metatype, value)
    const errors = await validate(obj)

    if (errors.length > 0) {
      throw new BadRequestException('Валидация провалилась')
    }

    value.id = parseInt(value.id);
    return value
  }

  private toValidate(metatype: Function): boolean {
    const types: Function[] = [String, Boolean, Number, Array, Object, UpdateExerciseDto]
    console.log(types.includes(metatype))
    return !types.includes(metatype)
  }
}