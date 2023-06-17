import ReactDOM from "react-dom";
import { useForm, SubmitHandler } from "react-hook-form";

interface IFormInput {
  latitude: String;
  longitude: string;
}

export default function Home() {
  const { getValues, handleSubmit, register } = useForm<IFormInput>();

  function onSubmit(data: IFormInput) {
    console.log("data", data);
  }

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <div className="flex">
        <div>
          <label>latitude</label>
          <input type="text" name="latitude" {...register} />
        </div>
        <div>
          <label>longitude</label>
          <input type="text" name="longitude" {...register} />
        </div>
      </div>
      <input type="submit" />
    </form>
  );
}
