
export class PayloadFCM {
    token : string;

    notification? : {
        title : string;
        body : string;   
    };

    data : any;
};