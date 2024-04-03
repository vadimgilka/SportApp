import { IsISO8601, IsInt, IsNotEmpty } from "class-validator";

export class HistoryDto {
    
    @IsNotEmpty()
    @IsInt()
    exerciseOnTrainId : number;

    @IsInt()
    authorId : number;

    @IsISO8601()
    createAt? : string;
}
