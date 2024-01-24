package ikhwan.hanif.elearningprototype;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

import ikhwan.hanif.elearningprototype.tutor.AddCourse;
import ikhwan.hanif.elearningprototype.tutor.TutorDashboard;

public class AdapterCourseListAdapter extends RecyclerView.Adapter<AdapterCourseListAdapter.viewHolder> {
    private Context context;
    private ArrayList<ModelCourse> videoArrayList;

    public AdapterCourseListAdapter(Context context, ArrayList<ModelCourse> videoArrayList) {
        this.context = context;
        this.videoArrayList = videoArrayList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_list_admin, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ModelCourse modelVideo = videoArrayList.get(position);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(modelVideo.getTimestamp()));
        String formattedDate = DateFormat.format("dd/MM/yyyy", calendar).toString();

        holder.tv_title.setText(modelVideo.getTitle());
        holder.tv_time.setText(formattedDate);
        holder.tv_category.setText(modelVideo.getCategory());
        holder.tv_totalLessons.setText(modelVideo.getTotalLessons() + " Kursus");
        holder.tv_tutor.setText(modelVideo.getTutor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayCourse.class);
                intent.putExtra("tutor", modelVideo.getTutor());
                intent.putExtra("courseTitle", modelVideo.getTitle());
                context.startActivity(intent);
            }
        });

        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Hapus")
                        .setMessage("Apakah anda yakin ingin menhapus kursus? ")
                        .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteVideo(modelVideo);
                            }
                        })
                        .setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();

            }
        });

    }

    private void deleteVideo(ModelCourse modelVideo) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(AddCourse.COURSES).child(modelVideo.getTutor());
        databaseReference.child(modelVideo.title)
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Berhasil Menghapus", Toast.LENGTH_SHORT).show();
                        TutorDashboard.getInstance().myMethod();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoArrayList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {

        TextView tv_title, tv_time, tv_totalLessons, tv_category, tv_tutor;
        ImageView iv_delete;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.titleTv);
            tv_time = itemView.findViewById(R.id.timeTv);
            tv_totalLessons = itemView.findViewById(R.id.totlaLessonsTv);
            tv_category = itemView.findViewById(R.id.categoryTv);
            tv_tutor = itemView.findViewById(R.id.tutorTv);

            iv_delete = itemView.findViewById(R.id.iv_delete);
        }
    }
}
