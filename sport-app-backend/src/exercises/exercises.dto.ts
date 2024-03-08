import { IsNotEmpty, MaxLength, MinLength } from 'class-validator';

const nameMinLength = 3;
const nameMaxLength = 256;
const descriptionMinLength = 10;
const descriptionMaxLength = 1024;

export class CreateExerciseDto {
  @IsNotEmpty()
  @MinLength(nameMinLength)
  @MaxLength(nameMaxLength)
  name: string;

  @IsNotEmpty()
  @MinLength(descriptionMinLength)
  @MaxLength(descriptionMaxLength)
  description: string;

  image?: string;
  gif?: string;
  video?: string;
}

export class UpdateExerciseDto {
  id: number;

  @IsNotEmpty()
  @MinLength(nameMinLength)
  @MaxLength(nameMaxLength)
  name: string;

  @IsNotEmpty()
  @MinLength(descriptionMinLength)
  @MaxLength(descriptionMaxLength)
  description: string;
  
  image?: string;
  gif?: string;
  video?: string;
  author_id : number;
}
