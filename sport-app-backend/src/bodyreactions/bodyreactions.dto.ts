import { IsNotEmpty, IsNumber, IsString } from "class-validator";
import exp from "constants";

export class CreateBodyReactionDTO{

    @IsString()
    @IsNotEmpty()
    name : string;

    @IsString()
    @IsNotEmpty()
    detail : string;
}


export class UpdateBodyReactionDTO {

    id : number
    @IsString()
    @IsNotEmpty()
    name : string;

    @IsString()
    @IsNotEmpty()
    detail : string;

    authorId? : number;
}