<?php

class Response
{
    public $status;//success, failed,...
    public $data; //array
    public $message;    
    public static $SUCCESS = "success";
    public static $FAILED = "failed";
    
    public function __construct($status, $data, $message)
    {
        $this->status = $status;
        $this->data = $data;
        $this->message = $message;        
    }

}

?>