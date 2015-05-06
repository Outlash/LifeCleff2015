<?php

$dir = "train";
$array_class=[];

if (is_dir($dir))
{
  if ($dh = opendir($dir))
  {

  	$data=array();
    while (($file = readdir($dh)) !== false)
    {
      if(file_exists("train/" . $file))
      {

            $feed =@file_get_contents("train/" . $file);
            $xml = simplexml_load_string($feed);
            $class = (string) $xml->ClassId;

            if(strlen($class)==6) {

                    $array_class[$class] = 1;

    //            if(isset($array_class)){
    //                echo var_dump($class)."DUBLU";
    //            }
            }

            foreach($array_class as $key){
//                echo $key;
                if(array_key_exists($class,$array_class))
                {
                    if(isset($array_class[$class]))
                    {
                        $array_class[$class]=$key+1;
                    }
                }
            }


		}
		else
		{
			exit('EROARE');
		}

    }
 	var_dump($array_class);
    closedir($dh);
  }
}

?>