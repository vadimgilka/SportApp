import {
  ArgumentMetadata,
  BadRequestException,
  Injectable,
  PipeTransform,
} from '@nestjs/common';
import { MuscleGroup } from '@prisma/client';
import {parseMuscleGroup} from '../utils/parse.muscle.group'

interface MuscleGroupPipeOptions {
  optional: boolean;
}

@Injectable()
export class MuscleGroupPipe
  implements PipeTransform<string, MuscleGroup>
{
  constructor(private readonly options: MuscleGroupPipeOptions = { optional : false}) {}

  transform(value: any, { metatype }: ArgumentMetadata): MuscleGroup {

    if(!this.options.optional){
      return null;
    }

    try {
      return parseMuscleGroup(value);
    }
    catch(e){
      throw new BadRequestException(e.message)
    }
  }
}
