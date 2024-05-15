import { Optional } from "@nestjs/common";
import { IsISO8601, IsInt, IsNotEmpty, IsString } from "class-validator";

export class CreateRemindDTO {

   
    @IsNotEmpty()
    @IsInt()
    biologicalAdditiveId : number;

    @IsNotEmpty()
    @IsInt()
    time : number;   


    @IsInt()         
    period : number;

    @IsISO8601()
    @Optional()
    last_reception?: string;

    @IsString()
    @Optional()
    token? : string;    
}


export class UpdateRemindDTO {

    id : number;

    @IsNotEmpty()
    @IsInt()
    time : number;   

    @IsInt()         
    period : number;

    @IsInt()
    measure : number;

    @IsInt()
    count_reception : number;

    @IsISO8601()
    @Optional()
    last_reception?: string;

    @IsString()
    @Optional()
    token? : string;
}