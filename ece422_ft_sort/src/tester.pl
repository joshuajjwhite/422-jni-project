#!/usr/bin/perl
#use strict;
use warnings;

use File::Spec;
use File::Basename;

my $input_file = File::Spec->rel2abs($ARGV[0]);

open( INPUT, "<$input_file") or die "Could not open perl input file\n";
my $output = "";

while (my $input_line = <INPUT> ){
	chomp($input_line);
	@string = split(/ /,$input_line);
}

my $pre = $string[0];
foreach my $num (@string){
     print("Failure: \nnum: " . $num . "\nless than\npre: " . $pre . "\n") if $num < $pre;
     die if $num < $pre;
     $pre = $num;
}