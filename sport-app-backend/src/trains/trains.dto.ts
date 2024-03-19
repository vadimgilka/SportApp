import { IsNotEmpty, Length, MaxLength, MinLength } from "class-validator";
import { ExerciseTrainDto } from "src/exercises/exercises.dto";

const nameLength = {
    min : 3,
    max : 256
};

const descriptionLength = {
    min : 10,
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

    exercises? : ExerciseTrainDto[];
}


export class UpdateTrainDto {

    @IsNotEmpty()
    @MinLength(nameLength.min)
    @MaxLength(nameLength.max)
    name? : string;

    @IsNotEmpty()
    @MinLength(descriptionLength.min)
    @MaxLength(descriptionLength.max)
    description? : string;

    exercises? : ExerciseTrainDto[];

}