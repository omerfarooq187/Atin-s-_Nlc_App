package com.example.atinsnlc.presentation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.atinsnlc.R
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

@Composable
fun CoursesCatalogScreen(navController: NavController) {
    CoursesContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoursesContent(navController: NavController) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Courses Catalog")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back",
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color("#013220".toColorInt()),
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(scrollState)
        ) {
            Text(
                stringResource(id = R.string.course_catalog_desc),
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(10.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .background(Color("#E65100".toColorInt()))
                    .padding(6.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "1. DAE in IOTAT\n(Internet of Things and Automation Technology)",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .weight(0.5f)
                )
                Image(
                    painter = painterResource(R.drawable.iotat),
                    contentDescription = "IOTAT",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(70.dp)
                )
            }
            Text(
                stringResource(id = R.string.IOTAT),
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(10.dp)
            )

            ExpandableCourseCard(title = "IOTAT First Year Subjects", subjects = iotFirstYearSubjects)
            ExpandableCourseCard(title = "IOTAT Second Year Subjects", subjects = iotSecondYearSubjects)
            ExpandableCourseCard(title = "IOTAT Third Year Subjects", subjects = iotThirdYearSubjects)
            Button(
                onClick = {
                    openPdfFile(context, "iot.pdf")
                },
                elevation = ButtonDefaults.buttonElevation(hoveredElevation = 40.dp),
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(
                    containerColor = Color("#E65100".toColorInt()),
                    contentColor = Color.White,
                    disabledContainerColor = Color("#9E9E9E".toColorInt()),
                    disabledContentColor = Color.White
                )
            ) {
                Text(
                    text = "IOTAT Detailed Subjects",
                    textAlign = TextAlign.Center
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .background(Color("#E65100".toColorInt()))
                    .padding(6.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "2. DAE in MMAT\n(Mechanical, Manufacturing, and Automation Technology)",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .weight(0.5f)
                )
                Image(
                    painter = painterResource(id = R.drawable.mmat),
                    contentDescription = "MMAT",
                    modifier = Modifier
                        .size(70.dp)
                )
            }

            Text(
                stringResource(id = R.string.MMAT),
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(10.dp)
            )
            ExpandableCourseCard(title = "MMAT First Year Subjects", subjects = mmatFirstYearSubjects)
            ExpandableCourseCard(title = "MMAT Second Year Subjects", subjects = mmatSecondYearSubjects)
            ExpandableCourseCard(title = "MMAT Third Year Subjects", subjects = mmatThirdYearSubjects)
            Button(
                onClick = {
                    openPdfFile(context, "mmat.pdf")
                },
                elevation = ButtonDefaults.buttonElevation(hoveredElevation = 40.dp),
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(
                    containerColor = Color("#E65100".toColorInt()),
                    contentColor = Color.White,
                    disabledContainerColor = Color("#9E9E9E".toColorInt()),
                    disabledContentColor = Color.White
                )
            ) {
                Text(
                    text = "MMAT Detailed Subjects",
                    textAlign = TextAlign.Center
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .background(Color("#E65100".toColorInt()))
                    .padding(6.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "3. DAE in CIT\n(Computer Information Technology)",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .weight(0.5f)
                )

                Image(
                    painter = painterResource(id = R.drawable.cit),
                    contentDescription = "CIT",
                    modifier = Modifier
                        .size(70.dp)
                )
            }

            Text(
                stringResource(id = R.string.CIT),
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(10.dp)
            )
            ExpandableCourseCard(title = "CIT First Year Subjects", subjects = citFirstYearSubjects)
            ExpandableCourseCard(title = "CIT Second Year Subjects", subjects = citSecondYearSubjects)
            ExpandableCourseCard(title = "CIT Third Year Subjects", subjects = citThirdYearSubjects)
            Button(
                onClick = {
                    openPdfFile(context, "cit_subjects.pdf")
                },
                elevation = ButtonDefaults.buttonElevation(hoveredElevation = 40.dp),
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(
                    containerColor = Color("#E65100".toColorInt()),
                    contentColor = Color.White,
                    disabledContainerColor = Color("#9E9E9E".toColorInt()),
                    disabledContentColor = Color.White
                )
            ) {
                Text(
                    text = "CIT Detailed Subjects",
                    textAlign = TextAlign.Center
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .background(Color("#E65100".toColorInt()))
                    .padding(6.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "4. DAE in CIVIL\n(Civil Engineering)",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .weight(0.5f)
                )

                Image(
                    painter = painterResource(id = R.drawable.civil),
                    contentDescription = "CIVIL",
                    modifier = Modifier
                        .size(70.dp)
                )
            }

            Text(
                stringResource(id = R.string.CIVIL),
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(10.dp)
            )

            ExpandableCourseCard(title = "Civil First Year Subjects", subjects = civilFirstYearSubjects)
            ExpandableCourseCard(title = "Civil Second Year Subjects", subjects = civilSecondYearSubjects)
            ExpandableCourseCard(title = "Civil Third Year Subjects", subjects = civilThirdYearSubjects)

            Button(
                onClick = {
                    openPdfFile(context, "civil_subjects.pdf")
                },
                elevation = ButtonDefaults.buttonElevation(hoveredElevation = 40.dp),
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(
                    containerColor = Color("#E65100".toColorInt()),
                    contentColor = Color.White,
                    disabledContainerColor = Color("#9E9E9E".toColorInt()),
                    disabledContentColor = Color.White
                )
            ) {
                Text(
                    text = "Civil Detailed Subjects",
                    textAlign = TextAlign.Center
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .background(Color("#E65100".toColorInt()))
                    .padding(6.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "5. DAE in MECHANICAL\n(Mechanical Engineering)",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .weight(0.5f)
                )

                Image(
                    painter = painterResource(id = R.drawable.mech),
                    contentDescription = "MECH",
                    modifier = Modifier
                        .size(70.dp)
                )

            }

            Text(
                stringResource(id = R.string.MECH),
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(10.dp)
            )

            ExpandableCourseCard(title = "Mechanical First Year Subjects", subjects = mechFirstYearSubjects)
            ExpandableCourseCard(title = "Mechanical Second Year Subjects", subjects = mechSecondYearSubjects)
            ExpandableCourseCard(title = "Mechanical Third Year Subjects", subjects = mechThirdYearSubjects)

            Button(
                onClick = {
                    openPdfFile(context, "metallurgy_welding.pdf")
                },
                elevation = ButtonDefaults.buttonElevation(hoveredElevation = 40.dp),
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(
                    containerColor = Color("#E65100".toColorInt()),
                    contentColor = Color.White,
                    disabledContainerColor = Color("#9E9E9E".toColorInt()),
                    disabledContentColor = Color.White
                )
            ) {
                Text(
                    text = "Mechanical Detailed Subjects",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

private fun openPdfFile(context: Context, fileName: String) {
    val assetManager: AssetManager = context.assets
    var `in`: InputStream? = null
    var out: OutputStream? = null
    val file = File(context.filesDir, fileName)

    try {
        `in` = assetManager.open(fileName)
        out = context.openFileOutput(fileName, Context.MODE_PRIVATE)
        copyFile(`in`, out)
    } catch (e: Exception) {
        Log.e("tag", e.message ?: "Unknown error")
    } finally {
        `in`?.close()
        out?.flush()
        out?.close()
    }

    val fileUri = FileProvider.getUriForFile(
        context,
        "com.example.atinsnlc.provider",
        file
    )

    val intent = Intent(Intent.ACTION_VIEW)
    intent.setDataAndType(fileUri, "application/pdf")
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, "No PDF viewer found", Toast.LENGTH_SHORT).show()
    }
}

@Throws(IOException::class)
private fun copyFile(`in`: InputStream, out: OutputStream) {
    val buffer = ByteArray(1024)
    var read: Int
    while (`in`.read(buffer).also { read = it } != -1) {
        out.write(buffer, 0, read)
    }
}

// course tables code
data class CourseDetails(
    val subjectCode: String,
    val subjectName: String
)

val citFirstYearSubjects = listOf(
    CourseDetails("GEN-111", "Islamiat & Pakistan Studies"),
    CourseDetails("ENG-112", "English"),
    CourseDetails("MATH-123", "Applied Mathematics-I"),
    CourseDetails("PHY-132", "Applied Physics"),
    CourseDetails("CH-132", "Applied Chemistry"),
    CourseDetails("OHSE-111", "Occupational Health, Safety & Environment"),
    CourseDetails("CIT-112", "Computer Application Software"),
    CourseDetails("CIT-113", "Introduction to Computer Programming"),
    CourseDetails("CIT-121", "General Engineering Workshop"),
    CourseDetails("CIT-134", "Electronics-I")
)

val citSecondYearSubjects = listOf(
    CourseDetails("GEN-211", "Islamiat & Pak Studies"),
    CourseDetails("MATH-233", "Applied Mathematics-II"),
    CourseDetails("MGM-211", "Business Communication"),
    CourseDetails("CIT-212", "Object-Oriented Programming with JAVA"),
    CourseDetails("CIT-223", "Computer Networks"),
    CourseDetails("CIT-235", "Micro-Processor Architecture"),
    CourseDetails("CIT-244", "Electronics-II"),
    CourseDetails("CIT-263", "Relational Data-Base Management System")
)

val citThirdYearSubjects = listOf(
    CourseDetails("GEN-311", "Islamiat & Pak Studies"),
    CourseDetails("ENG-311", "Technical Report Writing"),
    CourseDetails("MGT-331", "Management"),
    CourseDetails("CIT-303", "Web Development with JAVA"),
    CourseDetails("CIT-324", "Network Administration"),
    CourseDetails("CIT-333", "Operating System"),
    CourseDetails("CIT-344", "Graphic Designing"),
    CourseDetails("CIT-352", "PC System and Peripherals Repair"),
    CourseDetails("CIT-362", "Project")
)

val iotFirstYearSubjects = listOf(
    CourseDetails("GEN-111", "Islamiat & Pakistan Studies"),
    CourseDetails("ENG-112", "English"),
    CourseDetails("GENC-112", "Chinese Language-I"),
    CourseDetails("MATH-123", "Applied Mathematics-I"),
    CourseDetails("PHY-132", "Applied Physics"),
    CourseDetails("CH-132", "Applied Chemistry"),
    CourseDetails("OHSE-111", "Occupational Health, Safety & environment"),
    CourseDetails("CIT-112", "Computer Application Software"),
    CourseDetails("CIT-113", "Introduction to Computer Programming"),
    CourseDetails("IOT-112", "Python Programming"),
    CourseDetails("CIT-121", "General Engineering Workshop"),
    CourseDetails("CIT-134", "Electronics-I")
)

val iotSecondYearSubjects = listOf(
    CourseDetails("GEN-211", "Islamiat & Pak Studies"),
    CourseDetails("GENC-212", "Chinese Language-II"),
    CourseDetails("MGMC-212", "Understanding China"),
    CourseDetails("MATH-233", "Applied Mathematics-II"),
    CourseDetails("MGM-211", "Business Communication"),
    CourseDetails("CIT-212", "Object-Oriented Programming with JAVA"),
    CourseDetails("CIT-223", "Computer Networks"),
    CourseDetails("CIT-235", "Micro-Processor Architecture"),
    CourseDetails("CIT-233", "Operating System"),
    CourseDetails("IOT-212", "SCM Application Technology"),
    CourseDetails("CIT-244", "Electronics-II"),
    CourseDetails("CIT-263", "Relational Data-Base Management System"),
    CourseDetails("IOT-221", "Introduction to IoT Engineering")
)

val iotThirdYearSubjects = listOf(
    CourseDetails("GEN-311", "Islamiat & Pak Studies"),
    CourseDetails("CIT-303", "Web Development with JAVA"),
    CourseDetails("IOT-312", "Wireless Transmission Technology"),
    CourseDetails("IOT-322", "Identification Technology of IoT"),
    CourseDetails("IOT-332", "Embedded Technology of IoT"),
    CourseDetails("IOT-342", "Application Development of IoT"),
    CourseDetails("IOT-351", "Practical Project of IoT"),
    CourseDetails("IOT-363", "Post Practice")
)

val mmatFirstYearSubjects = listOf(
    CourseDetails("GEN-111", "Islamiat and Pak Studies"),
    CourseDetails("ENG-112", "English"),
    CourseDetails("GENC-112", "Chinese Language-I"),
    CourseDetails("MATH-113", "Applied Mathematics"),
    CourseDetails("PHY-122", "Applied Physics"),
    CourseDetails("CH-112", "Applied Chemistry"),
    CourseDetails("COMP-152", "Computer Applications"),
    CourseDetails(
        "MMAT-115",
        "Workshop Practice-Ⅰ (A) General Metal Work |n Workshop Practice-Ⅰ \n(B) Welding and Forging |n Workshop Practice-Ⅰ \n(C) Foundry |n Workshop Practice-Ⅰ \n(D) Basic Machine Shop-1"
    ),
    CourseDetails("MMAT-121", "History of Mechanical Industry"),
    CourseDetails("MMAT-132", "Basic Electrical and Electronics"),
    CourseDetails("MMAT-141", "Automation in Manufacturing"),
    CourseDetails("Mech-151", "Occupational Health Safety & Environment"),
    CourseDetails("Mech-173", "Engineering Drawing and Graphics")
)

val mmatSecondYearSubjects = listOf(
    CourseDetails("GEN-201", "Islamiat and Pak Studies"),
    CourseDetails("PHY-212", "Applied Mechanics"),
    CourseDetails("GENC-212", "Chinese Language-II"),
    CourseDetails("MGMC-212", "Understanding China"),
    CourseDetails("MATH-212", "Applied Mathematics-Ⅱ"),
    CourseDetails("MGM-201", "Communication Skills & Report Writing"),
    CourseDetails("MMAT-211", "Advanced Manufacturing Technology"),
    CourseDetails("MMAT-222", "Industrial Engineering And Quality Control"),
    CourseDetails("MMAT-232", "Microcontroller & PLC For Automation"),
    CourseDetails("Mech-244", "Workshop Practice-Ⅱ"),
    CourseDetails("MMAT-242", "Electrical Actuators and Drives"),
    CourseDetails("MMAT-251", "Metrology"),
    CourseDetails("MMAT-261", "Process control")
)

val mmatThirdYearSubjects = listOf(
    CourseDetails("GEN-301", "Islamiat and Pak Studies"),
    CourseDetails("MMAT-301", "Industrial Enterprise Management"),
    CourseDetails("MMAT-312", "Hydraulic and Pneumatic"),
    CourseDetails("MMAT-322", "Fault diagnosis and maintenance of CNC machine tools"),
    CourseDetails("MMAT-331", "Mechanical Manufacturing Technology"),
    CourseDetails("MMAT-342", "Fundamentals of Mechanical Design"),
    CourseDetails("MMAT-352", "Tooling design"),
    CourseDetails("MMAT-362", "Engineering Materials and Heat Treatment"),
    CourseDetails("MMAT-375", "Workshop Practice-Ⅲ"),
    CourseDetails("MMAT-382", "CAD/CAM"),
    CourseDetails("MMAT-392", "NC Machine Tool Technology")
)

val civilFirstYearSubjects = listOf(
    CourseDetails("GEN-111", "Islamiat & Pakistan Studies"),
    CourseDetails("ENG-112", "English"),
    CourseDetails("MATH-113", "Applied Mathematics-I"),
    CourseDetails("CH-112", "Applied Chemistry"),
    CourseDetails("PHY-122", "Applied Physics"),
    CourseDetails("Civil-104", "Basic Civil Engineering Surveying"),
    CourseDetails("Civil-113", "Engineering Materials & Construction Techniques"),
    CourseDetails("Civil-143", "Basic Civil Engineering Drawing"),
    CourseDetails("Shop-102", "Workshop Practice"),
    CourseDetails("Comp-111", "Computer Applications")
)

val civilSecondYearSubjects = listOf(
    CourseDetails("GEN-211", "Islamiat /Pakistan Studies"),
    CourseDetails("MATH-212", "Applied Mathematics-II"),
    CourseDetails("GEN-221", "Communication Skills & Report Writing"),
    CourseDetails("Civil-203", "Public Health Technology"),
    CourseDetails("Civil-214", "Advanced Civil Engineering Surveying"),
    CourseDetails("Civil-223", "Advanced Construction Techniques"),
    CourseDetails("Civil-232", "Quantity Surveying"),
    CourseDetails("Civil-243", "Civil Engineering Drawing & Auto CAD"),
    CourseDetails("Civil-263", "Engineering Mechanics"),
    CourseDetails("Civil-271", "Entrepreneurship")
)

val civilThirdYearSubjects = listOf(
    CourseDetails("GEN-311", "Islamiat & Pakistan Studies"),
    CourseDetails("Civil-303", "Advanced Quantity Surveying"),
    CourseDetails("Civil-314", "Construction Project Planning & Management"),
    CourseDetails("Civil-322", "Environment Health and Safety"),
    CourseDetails("Civil-334", "Hydraulics & Irrigation Engineering"),
    CourseDetails("Civil-343", "Transportation Engineering"),
    CourseDetails("Civil-354", "Concrete Technology and RCC Design"),
    CourseDetails("Civil-373", "Soil Mechanics & Bridge Engineering")
)

val mechFirstYearSubjects = listOf(
    CourseDetails("GEN-111", "Islamiat & Pakistan Studies"),
    CourseDetails("GEN-112", "English"),
    CourseDetails("MATH-113", "Applied Mathematics-I"),
    CourseDetails("PHY-122", "Applied Physics"),
    CourseDetails("Chem-122", "Applied Chemistry"),
    CourseDetails(
        "Workshop-127",
        "Workshop Practice-1(Th)\nFoundry Shop\nWelding Shop\nWood Shop\nMachine Shop\nMetal Shop"
    ),
    CourseDetails("Comp-152", "Computer Application"),
    CourseDetails("Mech-151", "Occupational Health Safety & Environment"),
    CourseDetails("CIT-121", "Engineering Drawing & Graphics")
)

val mechSecondYearSubjects = listOf(
    CourseDetails("GEN-211", "Islamiat & Pakistan Studies"),
    CourseDetails("PHY-212", "Applied Mechanics"),
    CourseDetails("MATH-211", "Applied Mathematics -II"),
    CourseDetails("MGM-212", "Business Communication & Report Writing"),
    CourseDetails("MGM-221", "Business Management and Industrial Economics"),
    CourseDetails("Elect-212", "Applied Electricity and Electronics"),
    CourseDetails("Mech-233", "Computer Aided Design (CAD)"),
    CourseDetails("Mech-246", "Workshop Practice-1 (Th) | Advance Welding (Pr) | Basic Machine ShopII (Pr) | Foundry and Pattern Making (Pr)"),
    CourseDetails("Mech-262", "Metallurgy"),
    CourseDetails("Mech-272", "Metrology")
)

val mechThirdYearSubjects = listOf(
    CourseDetails("GEN-311", "Islamiat & Pakistan Studies"),
    CourseDetails("Imh-301", "Industrial Management and Human Relations"),
    CourseDetails("Mech-302", "Fluid Mechanics and Hydraulic Machines"),
    CourseDetails("Mech-313", "Applied Thermodynamics"),
    CourseDetails("Mech-321", "Industrial Planning and Production Methods"),
    CourseDetails("Mech-333", "Machine Design & Analysis"),
    CourseDetails("Mech-363", "Tool & Mould Design"),
    CourseDetails("Mech-332", "Material Testing & Heat Treatment"),
    CourseDetails("Mech-354", "Workshop Practice-3"),
    CourseDetails("Mech-352", "CAD/CAM"),
    CourseDetails("Mech-372", "CNC Machine")
)

@Composable
fun CourseSubjects(subjects:List<CourseDetails>) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color("#E65100".toColorInt()))
                .padding(8.dp)
        ) {
            Text(
                text = "Subject Code",
                textAlign = TextAlign.Center,
                color = Color.White,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .weight(0.5f)
            )
            Text(
                text = "Subject Name",
                textAlign = TextAlign.Center,
                color = Color.White,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .weight(0.5f)
            )
        }

        subjects.forEach {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(4.dp)
            ) {
                Text(
                    text = it.subjectCode,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .weight(0.5f)
                )
                Text(
                    text = it.subjectName,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .weight(0.5f)
                )
            }
            HorizontalDivider(
                color = Color.Black
            )
        }
    }
}

@Composable
fun CourseTable(subjects: List<CourseDetails>) {

    CourseSubjects(subjects = subjects)
}

@Composable
fun ExpandableCourseCard(
    title:String,
    subjects:List<CourseDetails>
) {
    var expanded by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if(expanded) 180f else 0f, label = "")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RectangleShape,
        onClick = {
            expanded = !expanded
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(0.2f)
                        .rotate(rotationState),
                    onClick = {
                        expanded = !expanded
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }
            if (expanded) {
                CourseTable(subjects = subjects)
            }
        }

    }
}