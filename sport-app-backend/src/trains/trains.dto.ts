import { IsNotEmpty, Length, MaxLength, MinLength } from "class-validator";
import { ExerciseTrainDto } from "src/exercises/exercises.dto";

const nameLength = {
    min : 3,
    max : 256
};

const descriptionLength = {
    min : 3,
    max : 1024
};

export class CreateTrainDto {
    
    @IsNotEmpty()
    @MinLength(nameLength.min)
    @MaxLength(nameLength.max)
    name : string;

    @IsNotEmpty()
    @MinLength(descriptionLength.min)
    @MaxLength(descriptionLength.max)
    description : string;

    exercises : ExerciseTrainDto[];
    author_id? : number;
}


export class UpdateTrainDto {

    id : number;

    @IsNotEmpty()
    @MinLength(nameLength.min)
    @MaxLength(nameLength.max)
    name? : string;

    @IsNotEmpty()
    @MinLength(descriptionLength.min)
    @MaxLength(descriptionLength.max)
    description? : string;

    exercises? : ExerciseTrainDto[];
    author_id : number;

}