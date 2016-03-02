0. DESCRIPTION
--------------
The program runs a simulation of Hare (prey) and Puma (predator) densities within a finite
region across a time series using a pair of partial differential equations. The program
outputs regular "snapshots" of the puma and hare densities in a format that can be viewed
graphically (a PPM-format file) as well as average hare and puma densities calculated
across the total land region within the Landscape. All aspects of the model can be
configured on the command line.

Within the output PPM files (which are exported in series with increasing index number),
the Hare density is exported in the red channel, whilst the Puma density is exported in
the green channel. Water regions are identified in the PPM file by the presence of a
non-zero value in the blue channel. The values in the red and green channels in water
regions (i.e. in those regions where the blue channel is non-zero) are included for
cosmetic reasons and can be ignored.
                                                                                                    

I. PRE-REQUISITES
-----------------

In order to build, install and run this application you will need a working Java Software
Development Kit version 1.7 or higher. Java can be obtained from the Oracle Java web site
(https://www.java.com/) or can usually be installed using your favourite Operating
System's package manager.

You will also need a working version of Maven (https://maven.apache.org/). If you have
Maven already installed move to Section II. If not, then read on and follow the
instructions below.

1. Download Maven archive from the following link "https://maven.apache.org/download.cgi".

2. Ensure that the $JAVA_HOME environment variable is set and points to your JDK
installation.

3. Copy or move the Maven archive into your preferred location and unpack it by executing
the following command "unzip apache-maven-3.3.3-bin.zip" or "tar xzvf
apache-maven-3.3.3-bin.tar.gz" depending on the archive distribution you downloaded.

4. Make adjustments to your shell $PATH environment variable to allow the executable to be
found.

5. Open a shell and confirm the successful installation with "mvn -v".


II. INSTALLATION
-----------------

1. You will presumably have been supplied with a compressed tar archive of files which you
have successfully unpacked in order to access this file.

2. Working in the top-level directory of that uncompressed archive (which should contain a
file called 'pom.xml' and a directory called 'src'), run the command:

        make

This will control the building of the executable jar file and the project-associated web
site which contains documentation and metrics about the code.

In particular, it wraps a maven command:

        mvn clean install 

This will download all the libraries required by maven to compile the code. It will then
compile and build the project, running the complete test suite and reporting the results
of those tests as it goes. Should any tests fail, please contact the authors (see Section
VII)

NOTE:
The first time that maven runs any given command, it will need to download a significant
number of jar libraries which it uses to perform the actions requested. On a slow network
connection or on an account which has not run maven previously, this will take a
considerable length of time. You might like to go to get a coffee while maven is doing its
thing. Once the libraries have been downloaded the first time, they are cached locally (in
your ~/.m2 directory by default) so subsequent compilations will run much more quickly.


III. RUN SIMULATION
--------------------

1. We have provided a convenient wrapper script to make it easy to run the simulation.
From within the top-level directory, simply run:

        ./PreyPredatorSimulation

...supplying it with your chosen command-line options

For a full list of command-line options, see Section VI below


IV. EXAMPLE DATA
----------------

1. We have supplied two example Landscape Data Files (islands.dat, small.dat) and a
landscape filler file (seeds.dat) for testing purposes.

2. Working inside the top-level directory of the uncompressed archive (see Section III),
run the following command:

        ./PreyPredatorSimulation --landscape-filename islands.dat

3. The simulation will run (with random initial densities for hares and pumas) and will
generate a series of output PPM files in a date-stamped directory called
Simulation-<YYYYMMDD-HHmmss> (where <YYYYMMDD-HHmmss> is the time at which the simulation
was initiated. It will also write regular reports of the average Hare and Puma densities
(to STDOUT) and will finally report the length of time the simulation took. You can view
the PPM files with Gimp or a similar image viewing program to see the waves of hare and
puma population growth and decline.

4. Again working inside the top-level directory of the uncompressed archive file, run the
following command:

        ./PreyPredatorSimulation --landscape-filename small.dat \
                         --fill-method fromfile --densities-file seeds.dat

5. The program will read the landscape file from 'small.dat' and will then seed hare and
puma densities into the landscape based on the values in the landscape densities file
'seeds.dat'. The simulation will then proceed, again writing PPM format files into the
date and time-stamped directory. By using a variety of different density seed files,
different start points can be modelled.


V. PROJECT DOCUMENTATION
------------------------

1. The project documentation is generated automatically using maven. It should have been
built when you ran the make command described in Section II. If you need to build just the
documentation site then follow the instructions below.

2. Working in the top-level directory of the uncompressed archive (see Section II), run
the command:

        mvn site

This will generate a project web site which contains details of library dependencies, test
code coverage and other details. The root of this documentation web site can be found at
<project-directory>/target/site/index.html

3. We have provided a helpful documentation viewer script to launch the Firefox browser
(not supplied) with the project documentation page. Once you have run the maven command
(or the make command) to build the site, run:

        ./viewDocs

If you do not have the Firefox browser installed and available in your PATH as 'firefox'
then you will have to revert to opening the html file manually in your favourite web
browser application


IV. ARGUMENT LIST
------------------

This is a list of options provided for the Simulation.
The mandatory arguments are --landscape-filename and --output-filename-root.

    --delta-time, -d
       The time step for the simulation. The fraction of a full unit of time
       that should be considered to have elapsed between each simulated step.
       Default: 0.4
       
    --densities-file
       The path to a file of densities to be used in the initial set up of the
       Landscape. The file should be a plain text, space-delimited file. Each line should
       contain four values which represent the x-coordinate, y-coordinate, hare density
       and puma density respectively.
       
    --fill-method
       The method to be used to fill the initial landscape. Options are
       'random', 'constant' or 'fromfile'. If 'random' is selected, the user may also
       supply a maximum starting hare density and a maximum starting puma density by
       using the '--max-hare-start-density' and '--max-puma-start-density' flags
       respectively (default for each is 1.0). For the 'fromfile' option, the user must also
       specify a path to a file of densities using the --densities-file parameter.
       Default: random

    --hare-birth-rate, -r
       The birth rate of hares as a fraction of the current population
       Default: 0.08

    --hare-diffusion-rate, -k
       The diffusion rate for hares. The rate at which hares move between
       neighbouring regions within the Landscape.
       Default: 0.2

    --hare-predation-rate, -a
       The predation rate at which pumas eat hares
       Default: 0.04

    --landscape-filename
       Path to the file that defines the landscape. The landscape file will
       define the width and height of the Landscape and then will specify if a given
       region within that Landscape should be designated as Land (1) or Water (0).

    --max-hare-start-density
       The maximum starting value for the density of hares in a given Region.
       Values will be assigned at random between zero and this value which must be a
       positive real number.
       Default: 10.0

    --max-puma-start-density
       The maximum starting value for the density of pumas in a given Region.
       Values will be assigned at random between zero and this value which must be a
       positive real number.
       Default: 10.0

    --output-filename-root
       A path that defines the root name of the output files Each individual
       output will be named/numbered using suffixes to this root name. For example, if
       the output file root is specified as 'simulation1' then the program will
       export a series of PPM-formatted files called 'simulation1-step0097.ppm',
       'simulation1-step0180.ppm' etc.

    --output-timestep-interval, -s
       The number of time steps between outputs. If the user specifies a value
       of 10 for this parameter, then the application will export PPM and average
       density data files at step 0, step 10, step 20 etc. in the overall simulation.
       Default: 20

    --puma-birth-rate, -b
       The birth rate of pumas per one hare eaten
       Default: 0.02

    --puma-diffusion-rate, -l
       The diffusion rate for pumas.  The rate at which hares move between
       neighbouring regions within the Landscape.
       Default: 0.2

    --puma-mortality-rate, -m
       The puma mortality rate
       Default: 0.06

    --usage, --help, -h, -?
       Display this help message




VII. AUTHORS
------------
Andy Law (s1578554@sms.ed.ac.uk)
Sheng Wang (s1532126@sms.ed.ac.uk)
George (Giorgios) Zacharis (s1569743@sms.ed.ac.uk)
Konstantinos Zacharis (s1569729@sms.ed.ac.uk)




