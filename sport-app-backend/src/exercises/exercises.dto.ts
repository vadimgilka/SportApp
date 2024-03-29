import { Exercise } from '@prisma/client';
import { IsIn, IsInt, IsNotEmpty, IsNumber, MaxLength, MinLength } from 'class-validator';

const nameLength = {
  min : 3,
  max : 256
};

const descriptionLength = {
  min : 10,
  max : 1024
};

export class CreateExerciseDto {
  @IsNotEmpty()
  @MinLength(nameLength.min)
  @MaxLength(nameLength.max)
  name: string;

  @IsNotEmpty()
  @MinLength(descriptionLength.min)
  @MaxLength(descriptionLength.max)
  description: string;

  image?: string;
  video?: string;
}

export class UpdateExerciseDto {
  
  id: number;

  @IsNotEmpty()
  @MinLength(nameLength.min)
  @MaxLength(nameLength.max)
  name?: string;

  @IsNotEmpty()
  @MinLength(descriptionLength.min)
  @MaxLength(descriptionLength.max)
  description?: string;

  image?: string;
  video?: string;
  author_id: number;
}

export class ExerciseTrainDto {

    @IsNotEmpty()
    id : number

    @IsInt()
    repetition? : number;
    @IsInt()
    approach? : number;
    @IsInt()
    time? : number;
  
    @IsNotEmpty()
    @IsInt()
    exerciseNumber : number;
}